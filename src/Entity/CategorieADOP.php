<?php

namespace App\Entity;

use App\Repository\CategorieADOPRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: CategorieADOPRepository::class)]
class CategorieADOP
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\Column(length: 255)]
    private ?string $type = null;

    #[ORM\OneToMany(mappedBy: 'Categorie', targetEntity: Adoption::class)]
    private Collection $Animaux;

    public function __construct()
    {
        $this->Animaux = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getType(): ?string
    {
        return $this->type;
    }

    public function setType(string $type): self
    {
        $this->type = $type;

        return $this;
    }

    /**
     * @return Collection<int, Adoption>
     */
    public function getAnimaux(): Collection
    {
        return $this->Animaux;
    }

    public function addAnimaux(Adoption $animaux): self
    {
        if (!$this->Animaux->contains($animaux)) {
            $this->Animaux->add($animaux);
            $animaux->setCategorie($this);
        }

        return $this;
    }

    public function removeAnimaux(Adoption $animaux): self
    {
        if ($this->Animaux->removeElement($animaux)) {
            // set the owning side to null (unless already changed)
            if ($animaux->getCategorie() === $this) {
                $animaux->setCategorie(null);
            }
        }

        return $this;
    }
    public function __toString(): string
{
    return $this->getType();
}
}
