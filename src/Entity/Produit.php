<?php

namespace App\Entity;

use App\Repository\ProduitRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Serializer\Annotation\Groups;


#[ORM\Entity(repositoryClass: ProduitRepository::class)]
class Produit
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    #[Groups(['produit'])]
    private ?int $id = null;

    #[ORM\Column(length: 255)]
    #[Assert\NotBlank(message:"le champ est vide")]
    #[Groups(['produit'])]
    private ?string $nom = null;
   

    #[ORM\Column]
    #[Groups(['produit'])]
    private ?float $prix = null;

    #[ORM\Column(length: 255)]
    
    private ?string $photo = null;

    #[ORM\ManyToOne(inversedBy: 'produits')]
    #[ORM\JoinColumn(nullable: false)]
  
    private ?CategorieP $CategorieP = null;

    #[ORM\OneToMany(mappedBy: 'produit', targetEntity: ProductLike::class)]
    private Collection $Likes;


 


    public function __construct()
    {
        $this->Likes = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }

    public function getPrix(): ?float
    {
        return $this->prix;
    }

    public function setPrix(float $prix): self
    {
        $this->prix = $prix;

        return $this;
    }

    public function getPhoto(): ?string
    {
        return $this->photo;
    }

    public function setPhoto(string $photo): self
    {
        $this->photo = $photo;

        return $this;
    }

    public function getCategorieP(): ?CategorieP
    {
        return $this->CategorieP;
    }

    public function setCategorieP(?CategorieP $CategorieP): self
    {
        $this->CategorieP = $CategorieP;

        return $this;
    }

    
    public function __toString(){
        return(string) $this->getCategorieP();
    }

    /**
     * @return Collection<int, ProductLike>
     */
    public function getLikes(): Collection
    {
        return $this->Likes;
    }

    public function addLike(ProductLike $like): self
    {
        if (!$this->Likes->contains($like)) {
            $this->Likes->add($like);
            $like->setProduit($this);
        }

        return $this;
    }

    public function removeLike(ProductLike $like): self
    {
        if ($this->Likes->removeElement($like)) {
            // set the owning side to null (unless already changed)
            if ($like->getProduit() === $this) {
                $like->setProduit(null);
            }
        }

        return $this;
    }




}
