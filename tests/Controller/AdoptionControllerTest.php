<?php

namespace App\Test\Controller;

use App\Entity\Adoption;
use App\Repository\AdoptionRepository;
use Symfony\Bundle\FrameworkBundle\KernelBrowser;
use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class AdoptionControllerTest extends WebTestCase
{
    private KernelBrowser $client;
    private AdoptionRepository $repository;
    private string $path = '/adoption/';

    protected function setUp(): void
    {
        $this->client = static::createClient();
        $this->repository = static::getContainer()->get('doctrine')->getRepository(Adoption::class);

        foreach ($this->repository->findAll() as $object) {
            $this->repository->remove($object, true);
        }
    }

    public function testIndex(): void
    {
        $crawler = $this->client->request('GET', $this->path);

        self::assertResponseStatusCodeSame(200);
        self::assertPageTitleContains('Adoption index');

        // Use the $crawler to perform additional assertions e.g.
        // self::assertSame('Some text on the page', $crawler->filter('.p')->first());
    }

    public function testNew(): void
    {
        $originalNumObjectsInRepository = count($this->repository->findAll());

        $this->markTestIncomplete();
        $this->client->request('GET', sprintf('%snew', $this->path));

        self::assertResponseStatusCodeSame(200);

        $this->client->submitForm('Save', [
            'adoption[nom]' => 'Testing',
            'adoption[proprietaire]' => 'Testing',
            'adoption[age]' => 'Testing',
            'adoption[description]' => 'Testing',
            'adoption[sexe]' => 'Testing',
            'adoption[race]' => 'Testing',
            'adoption[Categorie]' => 'Testing',
        ]);

        self::assertResponseRedirects('/adoption/');

        self::assertSame($originalNumObjectsInRepository + 1, count($this->repository->findAll()));
    }

    public function testShow(): void
    {
        $this->markTestIncomplete();
        $fixture = new Adoption();
        $fixture->setNom('My Title');
        $fixture->setProprietaire('My Title');
        $fixture->setAge('My Title');
        $fixture->setDescription('My Title');
        $fixture->setSexe('My Title');
        $fixture->setRace('My Title');
        $fixture->setCategorie('My Title');

        $this->repository->save($fixture, true);

        $this->client->request('GET', sprintf('%s%s', $this->path, $fixture->getId()));

        self::assertResponseStatusCodeSame(200);
        self::assertPageTitleContains('Adoption');

        // Use assertions to check that the properties are properly displayed.
    }

    public function testEdit(): void
    {
        $this->markTestIncomplete();
        $fixture = new Adoption();
        $fixture->setNom('My Title');
        $fixture->setProprietaire('My Title');
        $fixture->setAge('My Title');
        $fixture->setDescription('My Title');
        $fixture->setSexe('My Title');
        $fixture->setRace('My Title');
        $fixture->setCategorie('My Title');

        $this->repository->save($fixture, true);

        $this->client->request('GET', sprintf('%s%s/edit', $this->path, $fixture->getId()));

        $this->client->submitForm('Update', [
            'adoption[nom]' => 'Something New',
            'adoption[proprietaire]' => 'Something New',
            'adoption[age]' => 'Something New',
            'adoption[description]' => 'Something New',
            'adoption[sexe]' => 'Something New',
            'adoption[race]' => 'Something New',
            'adoption[Categorie]' => 'Something New',
        ]);

        self::assertResponseRedirects('/adoption/');

        $fixture = $this->repository->findAll();

        self::assertSame('Something New', $fixture[0]->getNom());
        self::assertSame('Something New', $fixture[0]->getProprietaire());
        self::assertSame('Something New', $fixture[0]->getAge());
        self::assertSame('Something New', $fixture[0]->getDescription());
        self::assertSame('Something New', $fixture[0]->getSexe());
        self::assertSame('Something New', $fixture[0]->getRace());
        self::assertSame('Something New', $fixture[0]->getCategorie());
    }

    public function testRemove(): void
    {
        $this->markTestIncomplete();

        $originalNumObjectsInRepository = count($this->repository->findAll());

        $fixture = new Adoption();
        $fixture->setNom('My Title');
        $fixture->setProprietaire('My Title');
        $fixture->setAge('My Title');
        $fixture->setDescription('My Title');
        $fixture->setSexe('My Title');
        $fixture->setRace('My Title');
        $fixture->setCategorie('My Title');

        $this->repository->save($fixture, true);

        self::assertSame($originalNumObjectsInRepository + 1, count($this->repository->findAll()));

        $this->client->request('GET', sprintf('%s%s', $this->path, $fixture->getId()));
        $this->client->submitForm('Delete');

        self::assertSame($originalNumObjectsInRepository, count($this->repository->findAll()));
        self::assertResponseRedirects('/adoption/');
    }
}
