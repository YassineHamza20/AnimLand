<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class Front2Controller extends AbstractController
{
    #[Route('/front2', name: 'app_front2')]
    public function index(): Response
    {
        return $this->render('front2/index.html.twig', [
            'controller_name' => 'Front2Controller',
        ]);
    }
}
