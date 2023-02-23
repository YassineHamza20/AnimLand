<?php

namespace App\Controller;

use App\Entity\Adoption;
use App\Form\AdoptionType;
use App\Repository\AdoptionRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/adoption')]
class AdoptionController extends AbstractController
{
    #[Route('/', name: 'app_adoption_index', methods: ['GET'])]
    public function index(AdoptionRepository $adoptionRepository): Response
    {
        return $this->render('adoption/index.html.twig', [
            'adoptions' => $adoptionRepository->findAll(),
        ]);
    }

    #[Route('/new', name: 'app_adoption_new', methods: ['GET', 'POST'])]
    public function new(Request $request, AdoptionRepository $adoptionRepository): Response
    {
        $adoption = new Adoption();
        $form = $this->createForm(AdoptionType::class, $adoption);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $adoptionRepository->save($adoption, true);

            return $this->redirectToRoute('app_adoption_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('adoption/new.html.twig', [
            'adoption' => $adoption,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_adoption_show', methods: ['GET'])]
    public function show(Adoption $adoption): Response
    {
        return $this->render('adoption/show.html.twig', [
            'adoption' => $adoption,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_adoption_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Adoption $adoption, AdoptionRepository $adoptionRepository): Response
    {
        $form = $this->createForm(AdoptionType::class, $adoption);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $adoptionRepository->save($adoption, true);

            return $this->redirectToRoute('app_adoption_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('adoption/edit.html.twig', [
            'adoption' => $adoption,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_adoption_delete', methods: ['POST'])]
    public function delete(Request $request, Adoption $adoption, AdoptionRepository $adoptionRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$adoption->getId(), $request->request->get('_token'))) {
            $adoptionRepository->remove($adoption, true);
        }

        return $this->redirectToRoute('app_adoption_index', [], Response::HTTP_SEE_OTHER);
    }
}
