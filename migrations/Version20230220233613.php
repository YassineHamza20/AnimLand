<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20230220233613 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE adoption DROP FOREIGN KEY FK_EDDEB6A9BCF5E72D');
        $this->addSql('DROP INDEX IDX_EDDEB6A9BCF5E72D ON adoption');
        $this->addSql('ALTER TABLE adoption CHANGE categorie_id categorie_id_id INT NOT NULL');
        $this->addSql('ALTER TABLE adoption ADD CONSTRAINT FK_EDDEB6A98A3C7387 FOREIGN KEY (categorie_id_id) REFERENCES categorie_adop (id)');
        $this->addSql('CREATE INDEX IDX_EDDEB6A98A3C7387 ON adoption (categorie_id_id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE adoption DROP FOREIGN KEY FK_EDDEB6A98A3C7387');
        $this->addSql('DROP INDEX IDX_EDDEB6A98A3C7387 ON adoption');
        $this->addSql('ALTER TABLE adoption CHANGE categorie_id_id categorie_id INT NOT NULL');
        $this->addSql('ALTER TABLE adoption ADD CONSTRAINT FK_EDDEB6A9BCF5E72D FOREIGN KEY (categorie_id) REFERENCES categorie_adop (id)');
        $this->addSql('CREATE INDEX IDX_EDDEB6A9BCF5E72D ON adoption (categorie_id)');
    }
}
