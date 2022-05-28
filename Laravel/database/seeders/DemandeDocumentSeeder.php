<?php

namespace Database\Seeders;

use App\Models\DemandeDocument;
use Illuminate\Database\Seeder;

class DemandeDocumentSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
                DemandeDocument::factory(5)->create();
    }
}
