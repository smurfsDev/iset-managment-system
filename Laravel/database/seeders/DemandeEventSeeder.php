<?php

namespace Database\Seeders;

use App\Models\DemandeEvent;
use Illuminate\Database\Seeder;

class DemandeEventSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DemandeEvent::factory(5)->create();
        
    }
}
