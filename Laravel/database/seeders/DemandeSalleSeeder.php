<?php

namespace Database\Seeders;

use App\Models\DemandeSalle;
use Illuminate\Database\Seeder;

class DemandeSalleSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DemandeSalle::factory(5)->create();

    }
}
