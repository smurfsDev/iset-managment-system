<?php

namespace Database\Seeders;

use App\Models\Reclamation;
use Illuminate\Database\Seeder;

class ReclamationSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        Reclamation::factory(5)->create();
    }
}
