<?php

namespace App\Entity;

use App\Repository\CategorieVRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;


#[ORM\Entity(repositoryClass: CategorieVRepository::class)]
#[UniqueEntity('type')]
class CategorieV
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\Column(length: 255,unique:true)]
    #[Assert\NotBlank (message:"le champs est vide")]
    private ?string $type = null;

    #[ORM\OneToMany(mappedBy: 'category', targetEntity: ServiceV::class)]
    private Collection $serviceVs;

    public function __construct()
    {
        $this->serviceVs = new ArrayCollection();
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
     * @return Collection<int, ServiceV>
     */
    public function getServiceVs(): Collection
    {
        return $this->serviceVs;
    }

    public function addServiceV(ServiceV $serviceV): self
    {
        if (!$this->serviceVs->contains($serviceV)) {
            $this->serviceVs->add($serviceV);
            $serviceV->setCategory($this);
        }

        return $this;
    }

    public function removeServiceV(ServiceV $serviceV): self
    {
        if ($this->serviceVs->removeElement($serviceV)) {
            // set the owning side to null (unless already changed)
            if ($serviceV->getCategory() === $this) {
                $serviceV->setCategory(null);
            }
        }

        return $this;
    }

    public function __toString()
    {
        return $this->type; 

    // ...
}

}
