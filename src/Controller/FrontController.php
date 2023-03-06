<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Entity\Produit;
use App\Form\ProduitType;
use App\Repository\ProduitRepository;
use App\Entity\CategorieP;
use App\Form\ProductSearchType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\JsonResponse;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\SerializerInterface;
use Endroid\QrCode\Builder\BuilderInterface;
use App\Services\QrcodeService;
use App\Controller\FlashyNotifier;

class FrontController extends AbstractController
{
    #[Route('/front', name: 'app_front')]
    public function index(): Response
    {
        return $this->render('base.html.twig', [
            'controller_name' => 'FrontController',
        ]);
    }

    #[Route('/vet', name: 'app_vet')]
    public function vet(): Response
    {
        return $this->render('vet.html.twig', [
            'controller_name' => 'FrontController',
        ]);
    }
    #[Route('/services', name: 'app_services')]
    public function services(): Response
    {
        return $this->render('services.html.twig', [
            'controller_name' => 'FrontController',
        ]);
    }
    #[Route('/produits', name: 'app_produits')]
    public function produits(ProduitRepository $produitRepository, Request $request, PaginatorInterface $paginator, BuilderInterface $qrbuilder,  QrcodeService $qrcodeService): Response
    {
      
        
       
        $qrCode = null;
        $url = 'https://www.facebook.com/sarra.kachouandi/';
        $qrCode = $qrcodeService->qrcode($url);


        $res = $produitRepository->findAll(); 
        
        $res = $paginator->paginate(
            $res, /* query NOT result */
            $request->query->getInt('page', 1),
           6
        );

        $form = $this->createForm(ProductSearchType::class);
        $form->handleRequest($request);
       
    
        $produits = [];
        if ($form->isSubmitted() && $form->isValid()) {
            $criteria = $form->getData();
       
            $produits = $produitRepository->search($criteria);
        }
        
//    $serializer = new Serializer([ new ObjectNormalizer() ]);
//    $formatted = $serializer->normalize($produits);
//    return new JsonResponse($formatted);
// $json = $serializer->serialize($produits, 'json', ['groups' => "produits"]);

// //* Nous renvoyons une réponse Http qui prend en paramètre un tableau en format JSON
// return new Response($json);
    
        return $this->render('produits.html.twig', [
            'form' => $form->createView(),
            'produits' => $produits,
            'productliste' => $res, 
            'qrCode' => $qrCode,
            'controller_name' => 'ProduitController',
        ]);
        

      
            
     
    }


    #[Route('/Recherche', name: 'app_rechproduits')]
public function Rechercheproduits(ProduitRepository $produitRepository, Request $request): Response
{
    $sortOrder = $request->query->get('sort_order', 'asc');

    $produits = $produitRepository->findAll();  //recherche1


    if ($sortOrder === 'asc') {
        usort($produits, function($a, $b) {
            return $a->getPrix() - $b->getPrix();
        });
    } else {
        usort($produits, function($a, $b) {
            return $b->getPrix() - $a->getPrix();
        });
    
    }
   
    $form = $this->createForm(ProductSearchType::class);
    $form->handleRequest($request);
    
    
    if ($form->isSubmitted() && $form->isValid()) {
        $criteria = $form->getData();
       
        $produits = $produitRepository->search($criteria);
        }
       
  

    return $this->render('RechercherProduit.html.twig', [
        'form' => $form->createView(),
        'produits' => $produits,
         
        'sort_order' => $sortOrder,
        'controller_name' => 'ProduitController',
    ]);
    }
        



    #[Route('/rec', name: 'app_rec')]
    public function reclamations(): Response
    {
        return $this->render('reclamations.html.twig', [
            'controller_name' => 'FrontController',
        ]);
    }
    #[Route('/adoption', name: 'app_adoption')]
    public function adoption(): Response
    {
        return $this->render('adoption.html.twig', [
            'controller_name' => 'FrontController',
        ]);
    }
    #[Route('/about', name: 'app_about')]
    public function about(): Response
    {
        return $this->render('about.html.twig', [
            'controller_name' => 'FrontController',
        ]);
    }
    #[Route('/adop', name: 'app_adop')]
    public function adop(): Response
    {
        return $this->render('adoption.html.twig', [
            'controller_name' => 'FrontController',
        ]);
    }
    #[Route('/accoup', name: 'app_accoup')]
    public function accoup(): Response
    {
        return $this->render('accouplement.html.twig', [
            'controller_name' => 'FrontController',
        ]);
    }
    #[Route('/register', name: 'register_front')]
    public function register(): Response
    {
        return $this->render('back/register.html.twig', [
            'controller_name' => 'FrontController',
        ]);

    }

    #[Route('/login', name: 'login_front')]
    public function login(): Response
    {
        return $this->render('back/login.html.twig', [
            'controller_name' => 'FrontController',
        ]);

    }

   
    #[Route('Pagination', name: 'app_produit_page', )]
    public function pagination(ProduitRepository $produitRepository,PaginatorInterface $paginator, Request $request): Response
    {

        $produit = $produitRepository->findAll(); 
        $produit = $paginator->paginate(
            $produit, /* query NOT result */
            $request->query->getInt('page', 1),
            2
        );
        return $this->render('produits.html.twig', [
            'productliste' => $produit,
        ]);
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

    return $this->render('stats.html.twig', [
        'produit' => $produit,
        'counts' => $counts,
    ]);
}



    

}
