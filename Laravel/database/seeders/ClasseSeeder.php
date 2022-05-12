<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use App\Models\Classe;
class ClasseSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        Classe::factory(10)->create();
    }
}
