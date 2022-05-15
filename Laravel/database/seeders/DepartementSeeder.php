<?php

namespace Database\Seeders;

use App\Models\Departement;
use Illuminate\Database\Seeder;

class DepartementSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        Departement::create([
            'titre' => "Département Technologies de l'Informatique",
            "chef_departement_id" => 6,

        ]);
        Departement::create([
            'titre' => "Département Génie Electrique",
            "chef_departement_id" => 7,
        ]);
        Departement::create([
            'titre' => "Département Génie Mécanique",
            "chef_departement_id" => 8,
        ]);
        Departement::create([
            'titre' => "Département Administration d'affaire",
            "chef_departement_id" => 9,
        ]);
        Departement::create([
            'titre' => "Département Génie des procédés",
            "chef_departement_id" => 10,
        ]);
    }
}
