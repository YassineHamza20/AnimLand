<?php

namespace App\Entity;

use App\Repository\ProductLikeRepository;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: ProductLikeRepository::class)]
class ProductLike
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\Column(nullable: true)]
    private ?bool $Liked = null;

    #[ORM\ManyToOne(inversedBy: 'Likes')]
    private ?Produit $produit = null;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function isLiked(): ?bool
    {
        return $this->Liked;
    }

    public function setLiked(?bool $Liked): self
    {
        $this->Liked = $Liked;

        return $this;
    }

    public function getProduit(): ?Produit
    {
        return $this->produit;
    }

    public function setProduit(?Produit $produit): self
    {
        $this->produit = $produit;

        return $this;
    }
}
