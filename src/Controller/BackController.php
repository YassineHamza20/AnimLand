<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class BackController extends AbstractController
{
    #[Route('/back', name: 'app_back')]
    public function index(): Response
    {
        return $this->render('back/baseback.html.twig', [
            'controller_name' => 'BackController',
        ]);
    }
    #[Route('/cat', name: 'app_cat')]
    public function cat(): Response
    {
        return $this->render('back/cat.html.twig', [
            'controller_name' => 'BackController',
        ]);
    }
    
}
