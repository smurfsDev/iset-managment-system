<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use App\Models\DemandeAdhesionEvent;

class DemandeAdhesionEventSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DemandeAdhesionEvent::factory(5)->create();
    }
}
