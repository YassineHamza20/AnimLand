<?php

namespace App\Repository;

use App\Entity\Produit;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @extends ServiceEntityRepository<Produit>
 *
 * @method Produit|null find($id, $lockMode = null, $lockVersion = null)
 * @method Produit|null findOneBy(array $criteria, array $orderBy = null)
 * @method Produit[]    findAll()
 * @method Produit[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class ProduitRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Produit::class);
    }

    public function save(Produit $entity, bool $flush = false): void
    {
        $this->getEntityManager()->persist($entity);

        if ($flush) {
            $this->getEntityManager()->flush();
        }
    }

    public function remove(Produit $entity, bool $flush = false): void
    {
        $this->getEntityManager()->remove($entity);

        if ($flush) {
            $this->getEntityManager()->flush();
        }
    }

//    /**
//     * @return Produit[] Returns an array of Produit objects
//     */
//    public function findByExampleField($value): array
//    {
//        return $this->createQueryBuilder('p')
//            ->andWhere('p.exampleField = :val')
//            ->setParameter('val', $value)
//            ->orderBy('p.id', 'ASC')
//            ->setMaxResults(10)
//            ->getQuery()
//            ->getResult()
//        ;
//    }

//    public function findOneBySomeField($value): ?Produit
//    {
//        return $this->createQueryBuilder('p')
//            ->andWhere('p.exampleField = :val')
//            ->setParameter('val', $value)
//            ->getQuery()
//            ->getOneOrNullResult()
//        ;
//    }


public function search($criteria)
{
    $queryBuilder = $this->createQueryBuilder('p');

    if (!empty($criteria['nom'])) {
        $queryBuilder->andWhere('p.nom LIKE :nom')
                     ->setParameter('nom', '%' . $criteria['nom'] . '%');
    }

    // if (!empty($criteria['minPrice'])) {
    //     $queryBuilder->andWhere('p.prix >= :minPrice')
    //                  ->setParameter('minPrice', $criteria['minPrice']);
    // }

    // if (!empty($criteria['maxPrice'])) {
    //     $queryBuilder->andWhere('p.prix <= :maxPrice')
    //                  ->setParameter('maxPrice', $criteria['maxPrice']);
    // }

    if (array_key_exists('sortField', $criteria) && array_key_exists('sortDirection', $criteria)) {
        if ($criteria['sortField'] == 'nom') {
            $queryBuilder->orderBy('p.nom', $criteria['sortDirection']);
        } elseif ($criteria['sortField'] == 'prix') {
            $queryBuilder->orderBy('p.prix', $criteria['sortDirection']);
        }
    }

    return $queryBuilder->getQuery()->getResult();
}


private $sortField;

public function setSortField(string $sortField): void
{
    $this->sortField = $sortField;
}


}
