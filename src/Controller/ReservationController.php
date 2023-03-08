<?php

namespace App\Controller;

use App\Entity\Reservation;
use App\Repository\ReservationRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

use Dompdf\Dompdf;
use Dompdf\Options;

#[Route('/reservation')]
class ReservationController extends AbstractController
{
    #[Route('/', name: 'app_reser', methods: ['GET'])]
    public function index(ReservationRepository $reservationRepository): Response
    {
        return $this->render('reservation/index.html.twig', [
            'reservation' =>  $reservationRepository->findAll(),
        ]);
    }

    #[Route('/{id}', name: 'app_reser_show', methods: ['GET'])]
    public function show(Reservation $reservation): Response
    {
        return $this->render('reservation/show.html.twig', [
            'reservation' => $reservation,
        ]);
    }


    #[Route('/{id}/pdf', name: 'app_reser_pdf', methods: ['GET'])]
    public function pdf(Reservation $reservation): Response
    {

        $tableHTML = $reservation->getId() . "<br/>" . $reservation->getUserId()->getEmail() . "<br/>" . $reservation->getServiceId()->getNom() . "<br/>" . $reservation->getServiceId()->getPrix() . "<br/>" . $reservation->getDate()->format('Y-m-d H:i:s');

        $pdfOption = new Options();
        $pdfOption->set('defaultFont', 'sans-serif');

        $dompdf = new Dompdf($pdfOption);
        $dompdf->loadHtml($tableHTML);

        $dompdf->setPaper('A4', 'portrait');

        $dompdf->render();

        $pdfContent = $dompdf->output();


        return new Response($pdfContent, 200, array(
            'Content-Type' => 'application/pdf'
        ));
    }
}
