<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20230302192453 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE UNIQUE INDEX UNIQ_11064A068CDE5729 ON categorie_v (type)');
        $this->addSql('ALTER TABLE service_v ADD CONSTRAINT FK_6648BB3712469DE2 FOREIGN KEY (category_id) REFERENCES categorie_v (id)');
        $this->addSql('CREATE INDEX IDX_6648BB3712469DE2 ON service_v (category_id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('DROP INDEX UNIQ_11064A068CDE5729 ON categorie_v');
        $this->addSql('ALTER TABLE service_v DROP FOREIGN KEY FK_6648BB3712469DE2');
        $this->addSql('DROP INDEX IDX_6648BB3712469DE2 ON service_v');
    }
}
