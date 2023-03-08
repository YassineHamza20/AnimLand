<?php

namespace App\Controller;

use App\Entity\Reservation;
use App\Entity\ServiceV;
use App\Form\ReservationType;
use App\Repository\ReservationRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\ServiceVRepository;
use Doctrine\ORM\EntityManagerInterface;
use Doctrine\Persistence\ManagerRegistry;
use Psr\Log\LoggerInterface;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\Mime\Email;
use Knp\Component\Pager\PaginatorInterface;
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
    public function vet(Request $request,ServiceVRepository $serviceVRepository,PaginatorInterface $paginator): Response
    {   $res = $serviceVRepository->findAll();
        $res = $paginator->paginate(
        $res, /* query NOT result */
        $request->query->getInt('page', 1),
       4
    );
        return $this->render('vet.html.twig', [
        
            'service_vs' => $res,
        ]);
    }
     //eli fiha reservation w nombre de vue w email
    #[Route('/vet/{id}', name: 'app_vet_show', methods: ['GET', 'POST'])]
    public function showVet(LoggerInterface $logger, MailerInterface $mailer, ServiceV $service, Request $request, ReservationRepository $reservationRepository, ManagerRegistry $doctrine, EntityManagerInterface $entityManager): Response
    {


        $service->setViewCount($service->getViewCount() + 1);
        $entityManager->flush();

        $reserv = new Reservation();
        $form = $this->createForm(ReservationType::class, $reserv);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $reserv->setServiceId($service);
            $entityManager = $doctrine->getManager();
            $entityManager->persist($reserv);

            $reservationRepository->save($reserv, true);

            $email = (new Email())
                ->from('yasmine.jebaliii@gmail.com')
                ->to($reserv->getUserId()->getEmail())
                ->subject('Reservation Confirmed')
                ->text('Your reservation has been confirmed');

            try {
                $mailer->send($email);
                $logger->info('Email sent successfully.');
            } catch (\Exception $e) {
                $logger->error('Error sending email: ' . $e->getMessage());
            }

            return $this->render('vet/show.html.twig', [
                'service_v' => $service,
                'form' => $form->createView(),
            ]);
        }


        return $this->render('vet/show.html.twig', [
            'service_v' => $service,
            'form' => $form->createView(),
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
    public function produits(): Response
    {
        return $this->render('produits.html.twig', [
            'controller_name' => 'FrontController',
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
}
