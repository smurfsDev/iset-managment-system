<?php

namespace Database\Seeders;

use App\Models\DemandeAdhesionEvent;
use Illuminate\Database\Seeder;


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
