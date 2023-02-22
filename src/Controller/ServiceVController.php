<?php

namespace App\Controller;

use App\Entity\ServiceV;
use App\Form\ServiceVType;
use App\Repository\ServiceVRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/service/v')]
class ServiceVController extends AbstractController
{
    #[Route('/', name: 'app_service_v_index', methods: ['GET'])]
    public function index(ServiceVRepository $serviceVRepository): Response
    {
        return $this->render('service_v/index.html.twig', [
            'service_vs' => $serviceVRepository->findAll(),
        ]);
    }

    #[Route('/new', name: 'app_service_v_new', methods: ['GET', 'POST'])]
    public function new(Request $request, ServiceVRepository $serviceVRepository): Response
    {
        $serviceV = new ServiceV();
        $form = $this->createForm(ServiceVType::class, $serviceV);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $serviceVRepository->save($serviceV, true);

            return $this->redirectToRoute('app_service_v_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('service_v/new.html.twig', [
            'service_v' => $serviceV,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_service_v_show', methods: ['GET'])]
    public function show(ServiceV $serviceV): Response
    {
        return $this->render('service_v/show.html.twig', [
            'service_v' => $serviceV,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_service_v_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, ServiceV $serviceV, ServiceVRepository $serviceVRepository): Response
    {
        $form = $this->createForm(ServiceVType::class, $serviceV);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $serviceVRepository->save($serviceV, true);

            return $this->redirectToRoute('app_service_v_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('service_v/edit.html.twig', [
            'service_v' => $serviceV,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_service_v_delete', methods: ['POST'])]
    public function delete(Request $request, ServiceV $serviceV, ServiceVRepository $serviceVRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$serviceV->getId(), $request->request->get('_token'))) {
            $serviceVRepository->remove($serviceV, true);
        }

        return $this->redirectToRoute('app_service_v_index', [], Response::HTTP_SEE_OTHER);
    }
}
