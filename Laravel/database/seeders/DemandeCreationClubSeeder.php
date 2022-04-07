<?php

namespace Database\Seeders;

use App\Models\DemandeCreationClub;
use Illuminate\Database\Seeder;

class DemandeCreationClubSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DemandeCreationClub::factory(5)->create();
    }
}
