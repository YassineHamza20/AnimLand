<?php

namespace App\Controller;

use App\Entity\Reclamation;
use App\Form\ReclamationType;
use App\Repository\ReclamationRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
 
 
#[Route('/')] 
class ReclamationController extends AbstractController
{
    #[Route('/reclamation', name: 'app_reclamation', methods: ['GET'])]
    public function reclamation(ReclamationRepository $reclamationRepository): Response
    {
        return $this->render('front/reclamation/index.html.twig', [
            'reclamations' => $reclamationRepository->findAll(),
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
    public function new(Request $request, ReclamationRepository $reclamationRepository): Response
    {
        $reclamation = new Reclamation();
        $form = $this->createForm(ReclamationType::class, $reclamation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $reclamationRepository->save($reclamation, true);

            return $this->redirectToRoute('app_reclamation', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('front/reclamation/new.html.twig', [
            'reclamation' => $reclamation,
            'form' => $form,
        ]);
    }
 

    #[Route('/{id}', name: 'app_reclamation_show', methods: ['GET'])]
    public function show(Reclamation $reclamation): Response
    {
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

