<?php

namespace Database\Seeders;

use App\Models\DemandeMateriel;
use Illuminate\Database\Seeder;

class DemandeMaterielsSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DemandeMateriel::factory(55)->create();
    }
}
