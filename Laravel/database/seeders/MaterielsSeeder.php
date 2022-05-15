<?php

namespace Database\Seeders;

use App\Models\Materiel;
use Illuminate\Database\Seeder;

class MaterielsSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        Materiel::factory(10)->create();
    }
}
