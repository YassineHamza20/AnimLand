<?php

namespace App\Controller;


use App\Entity\Reclamation;
use App\Form\ReclamationType;
use App\Repository\ReclamationRepository;
use MercurySeries\FlashyBundle\FlashyNotifier;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;

use Knp\Component\Pager\PaginatorInterface;
use Dompdf\Dompdf;
use Dompdf\Options; 


 
#[Route('/')] 
class ReclamationController extends AbstractController
{
  
    #[Route('/reclamation', name: 'app_reclamation', methods: ['GET'])]
    public function reclamation(FlashyNotifier $flashy,ReclamationRepository $reclamationRepository, Request $request, PaginatorInterface $paginator): Response
    {
        $reclamation = $reclamationRepository->findAll();  //recherche1
        $reclamation = $paginator->paginate(
            $reclamation, /* query NOT result */
            $request->query->getInt('page', 1),
           5
        );
            $flashy->mutedDark('Bienvenue', 'http://127.0.0.1:8000/app_a');
        
        return $this->render('front/reclamation/index.html.twig', [
         //   'reclamations' => $reclamationRepository->findAll(),
            'reclamation' => $reclamation,
        ]);
    }
 

    #[Route('/{id}/print', name: 'pdf')]
    public function printReclamation(Reclamation $reclamation): Response
    {
        // Render the view to HTML
        $html = $this->renderView('front/reclamation/pdf.html.twig', [
            'title' => 'My PDF',
            'reclamation' => $reclamation,
        ]);
    
        // Create a new Dompdf instance
        $dompdf = new Dompdf();
    
        // Load the HTML into the Dompdf instance
        $dompdf->loadHtml($html);
    
        // Set the paper size and orientation
        $dompdf->setPaper('A4', 'portrait');
    
        // Render the PDF
        $dompdf->render();
    
        // Generate the response containing the PDF
        $response = new Response($dompdf->output());
        $response->headers->set('Content-Type', 'application/pdf');
        $response->headers->set('Content-Disposition', 'attachment;filename="reclamation_' . $reclamation->getId() . '.pdf"');
    
        return $response;
    }
    
    

 
    public function reclamations(reclamationRepository $reclamationRepository, Request $request, PaginatorInterface $paginator): Response
    {
        $reclamation = $reclamationRepository->findAll();  //recherche1
        $reclamation = $paginator->paginate(
            $reclamation, /* query NOT result */
            $request->query->getInt('page', 1),6);
             return $this->render('front/reclamation/index.html.twig', [
            'form' => $form->createView(),
            'reclamation' => $reclamation,
            'reclamationliste' => $res, 
            'controller_name' => 'reclamationController',
        ]);
    }



    #[Route('/app_ad', name: 'app_ad')]
    public function index(): Response
    {
        return $this->render('adoption.html.twig', [
            'controller_name' => 'ReclamationController',
        ]);
    } 

    #[Route('/app_pr', name: 'app_pr')]
    public function indexpr(): Response
    {
        return $this->render('produits.html.twig', [
            'controller_name' => 'ReclamationController',
        ]);
    } 
    #[Route('/app_ch', name: 'app_ch')]
    public function indexch(): Response
    {
        return $this->render('accouplement.html.twig', [
            'controller_name' => 'ReclamationController',
        ]);
    } 

    #[Route('/app_s', name: 'app_s')]
    public function indexs(): Response
    {
        return $this->render('services.html.twig', [
            'controller_name' => 'ReclamationController',
        ]);
    } 
    #[Route('/app_v', name: 'app_v')]
    public function indexv(): Response
    {
        return $this->render('vet.html.twig', [
            'controller_name' => 'ReclamationController',
        ]);
    } 
    #[Route('/app_a', name: 'app_a')]
    public function indexa(): Response
    {
        return $this->render('base.html.twig', [
            'controller_name' => 'ReclamationController',
        ]);
    } 

  
    #[Route('/new', name: 'app_reclamation_new', methods: ['GET', 'POST'])]
    public function new(FlashyNotifier $flashy,Request $request, ReclamationRepository $reclamationRepository): Response
    {
        $reclamation = new Reclamation();
        $form = $this->createForm(ReclamationType::class, $reclamation);
        $form->handleRequest($request);
       

        if ($form->isSubmitted() && $form->isValid()) {
            $reclamationRepository->save($reclamation, true);
            
            return $this->redirectToRoute('app_reclamation', [], Response::HTTP_SEE_OTHER);
        }
        
      
        $flashy->mutedDark('Creation', 'http://127.0.0.1:8000/app_a');
        
        return $this->renderForm('front/reclamation/new.html.twig', [
         //   'reclamations' => $reclamationRepository->findAll(),
            'reclamation' => $reclamation,
            'form' => $form,
        ]);
    }
    
    #[Route('/{id}', name: 'app_reclamation_show', methods: ['GET'])]
    public function show(FlashyNotifier $flashy,Reclamation $reclamation): Response
    {
        $flashy->mutedDark('Show', 'http://127.0.0.1:8000/app_a');
        return $this->render('front/reclamation/show.html.twig', [
            'reclamation' => $reclamation,
        ]);
    }



    #[Route('/{id}/edit', name: 'app_reclamation_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Reclamation $reclamation, ReclamationRepository $reclamationRepository): Response
    {
        $form = $this->createForm(ReclamationType::class, $reclamation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $reclamationRepository->save($reclamation, true);

            return $this->redirectToRoute('app_reclamation', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('front/reclamation/edit.html.twig', [
            'reclamation' => $reclamation,
            'form' => $form,
        ]);
    }


   

    #[Route('/{id}', name: 'app_reclamation_delete', methods: ['POST'])]
    public function delete(Request $request, Reclamation $reclamation, ReclamationRepository $reclamationRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$reclamation->getId(), $request->request->get('_token'))) {
            $reclamationRepository->remove($reclamation, true);
        }

        return $this->redirectToRoute('app_reclamation', [], Response::HTTP_SEE_OTHER);
    }
 


}
 