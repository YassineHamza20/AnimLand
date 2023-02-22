<?php

namespace App\Controller;

use App\Entity\CategorieV;
use App\Form\CategorieVType;
use App\Repository\CategorieVRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/categorie/v')]
class CategorieVController extends AbstractController
{
    #[Route('/', name: 'app_categorie_v_index', methods: ['GET'])]
    public function index(CategorieVRepository $categorieVRepository): Response
    {
        return $this->render('categorie_v/index.html.twig', [
            'categorie_vs' => $categorieVRepository->findAll(),
        ]);
    }

    #[Route('/new', name: 'app_categorie_v_new', methods: ['GET', 'POST'])]
    public function new(Request $request, CategorieVRepository $categorieVRepository): Response
    {
        $categorieV = new CategorieV();
        $form = $this->createForm(CategorieVType::class, $categorieV);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $categorieVRepository->save($categorieV, true);

            return $this->redirectToRoute('app_categorie_v_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('categorie_v/new.html.twig', [
            'categorie_v' => $categorieV,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_categorie_v_show', methods: ['GET'])]
    public function show(CategorieV $categorieV): Response
    {
        return $this->render('categorie_v/show.html.twig', [
            'categorie_v' => $categorieV,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_categorie_v_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, CategorieV $categorieV, CategorieVRepository $categorieVRepository): Response
    {
        $form = $this->createForm(CategorieVType::class, $categorieV);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $categorieVRepository->save($categorieV, true);

            return $this->redirectToRoute('app_categorie_v_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('categorie_v/edit.html.twig', [
            'categorie_v' => $categorieV,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_categorie_v_delete', methods: ['POST'])]
    public function delete(Request $request, CategorieV $categorieV, CategorieVRepository $categorieVRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$categorieV->getId(), $request->request->get('_token'))) {
            $categorieVRepository->remove($categorieV, true);
        }

        return $this->redirectToRoute('app_categorie_v_index', [], Response::HTTP_SEE_OTHER);
    }
}
