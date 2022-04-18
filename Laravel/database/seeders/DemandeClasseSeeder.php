<?php

namespace Database\Seeders;

use App\Models\DemandeClasse;
use Illuminate\Database\Seeder;

class DemandeClasseSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DemandeClasse::factory(5)->create();

    }
}
