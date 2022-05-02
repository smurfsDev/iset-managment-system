<?php

namespace Database\Seeders;

use App\Models\CategorieMateriel;
use Illuminate\Database\Seeder;

class CategoryMaterielsSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        CategorieMateriel::factory(5)->create();
    }
}
