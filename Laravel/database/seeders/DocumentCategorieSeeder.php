<?php

namespace Database\Seeders;

use App\Models\DocumentCategorie;
use Illuminate\Database\Seeder;

class DocumentCategorieSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
       
        DocumentCategorie::factory(5)->create();
    }
}
