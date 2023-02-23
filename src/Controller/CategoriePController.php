<?php

namespace App\Controller;

use App\Entity\CategorieP;
use App\Form\CategoriePType;
use App\Repository\CategoriePRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/categorie/p')]
class CategoriePController extends AbstractController
{
    #[Route('/', name: 'app_categorie_p_index', methods: ['GET'])]
    public function index(CategoriePRepository $categoriePRepository): Response
    {
        return $this->render('categorie_p/index.html.twig', [
            'categorie_ps' => $categoriePRepository->findAll(),
        ]);
    }

    #[Route('/new', name: 'app_categorie_p_new', methods: ['GET', 'POST'])]
    public function new(Request $request, CategoriePRepository $categoriePRepository): Response
    {
        $categorieP = new CategorieP();
        $form = $this->createForm(CategoriePType::class, $categorieP);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $categoriePRepository->save($categorieP, true);

            return $this->redirectToRoute('app_categorie_p_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('categorie_p/new.html.twig', [
            'categorie_p' => $categorieP,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_categorie_p_show', methods: ['GET'])]
    public function show(CategorieP $categorieP): Response
    {
        return $this->render('categorie_p/show.html.twig', [
            'categorie_p' => $categorieP,
        ]);
    }







    #[Route('filtre/{id}', name: 'app_categorie_p_show_filtre',)]
    public function show1(CategorieP $categorieP): Response
    {
        return $this->render('produits.html.twig', [
            'categorie_p' => $categorieP,
        ]);


    }





    #[Route('/{id}/edit', name: 'app_categorie_p_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, CategorieP $categorieP, CategoriePRepository $categoriePRepository): Response
    {
        $form = $this->createForm(CategoriePType::class, $categorieP);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $categoriePRepository->save($categorieP, true);

            return $this->redirectToRoute('app_categorie_p_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('categorie_p/edit.html.twig', [
            'categorie_p' => $categorieP,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_categorie_p_delete', methods: ['POST'])]
    public function delete(Request $request, CategorieP $categorieP, CategoriePRepository $categoriePRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$categorieP->getId(), $request->request->get('_token'))) {
            $categoriePRepository->remove($categorieP, true);
        }

        return $this->redirectToRoute('app_categorie_p_index', [], Response::HTTP_SEE_OTHER);
    }
}
