<?php

namespace App\Controller;

use App\Entity\Produit;
use App\Form\ProduitType;
use App\Repository\ProduitRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\String\Slugger\SluggerInterface;
use Symfony\Component\Filesystem\Filesystem;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\SerializerInterface;
use Symfony\Component\HttpFoundation\JsonResponse;


#[Route('/produit')]
class ProduitController extends AbstractController
{
    #[Route('/', name: 'app_produit_index', methods: ['GET'])]
    public function index(ProduitRepository $produitRepository): Response
    {
        
        $res = $produitRepository->findAll(); 


        
        return $this->render('produit/index.html.twig', [
            'controller_name' => 'ProduitController',
            'productliste' => $res, 
        ]);
      
    }



    



    #[Route('/new', name: 'app_produit_new', methods: ['GET', 'POST'])]
    public function new(Request $request, ProduitRepository $produitRepository, SluggerInterface $slugger): Response
    {
        $produit = new Produit();
        $form = $this->createForm(ProduitType::class, $produit);
        $form->handleRequest($request);
       
        $filesystem = new Filesystem();
        if ($form->isSubmitted() && $form->isValid()) {
            $produitRepository->save($produit, true);

        
           
            $uploadedFile = $form->get('photo')->getData();
            $formData =  $uploadedFile->getPathname();
            $sourcePath = strval($formData);
            $destinationPath = 'uploads/photos/photo'.strval($produit->getId()).'.png';
            $produit->setPhoto($destinationPath);
            $filesystem->copy($sourcePath, $destinationPath);
        
            $produitRepository->save($produit, true);
            return $this->redirectToRoute('app_produit_index', [], Response::HTTP_SEE_OTHER);

        }
        return $this->renderForm('produit/new.html.twig', [
            'produit' => $produit,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_produit_show', methods: ['GET'])]
    public function show(Produit $produit): Response
    {
        return $this->render('produit/show.html.twig', [
            'produit' => $produit,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_produit_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Produit $produit, ProduitRepository $produitRepository): Response
    {
        $form = $this->createForm(ProduitType::class, $produit);
        $form->handleRequest($request);
        $filesystem = new Filesystem();
        if ($form->isSubmitted() && $form->isValid()) {
            $produitRepository->save($produit, true);


            $uploadedFile = $form->get('photo')->getData();
            $formData =  $uploadedFile->getPathname();
            $sourcePath = strval($formData);
            $destinationPath = 'uploads/photos/photo'.strval($produit->getId()).'.png';
            $produit->setPhoto($destinationPath);
            $filesystem->copy($sourcePath, $destinationPath);
        
            $produitRepository->save($produit, true);

            return $this->redirectToRoute('app_produit_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('produit/edit.html.twig', [
            'produit' => $produit,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_produit_delete', methods: ['POST'])]
    public function delete(Request $request, Produit $produit, ProduitRepository $produitRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$produit->getId(), $request->request->get('_token'))) {
            $produitRepository->remove($produit, true);
        }

        return $this->redirectToRoute('app_produit_index', [], Response::HTTP_SEE_OTHER);
    }

   
    #[Route('/produits/stats', name: 'app_produit_stats', methods: ['GET'])]
    public function produitsstats(): Response
    {
        $entityManager = $this->getDoctrine()->getManager();
        $repository = $entityManager->getRepository(Produit::class);
    
        // Count total number of annonces
        $totalProduits = $repository->createQueryBuilder('a')
            ->select('COUNT(a.id)')
            ->getQuery()
            ->getSingleScalarResult();
    
        // Query for all products and group them by category
        $query = $repository->createQueryBuilder('a')
            ->select('a.prix as prix, COUNT(a.id) as count, COUNT(a.id) / :total * 100 as percentage')
            ->setParameter('total', $totalProduits)
            ->groupBy('a.prix')
            ->getQuery();
    
    
    
            
    
        $produit = $query->getResult();
    
        
    
        // Calculate the counts array
        $counts = [];
        foreach ($produit as $item) {
            $counts[$item['prix']] = $item['count'];
        
        }
    
        return $this->render('produit/stats.html.twig', [
            'produit' => $produit,
            'counts' => $counts,
        ]);
    }
    

    

}
