<?php

namespace Database\Seeders;

use App\Models\Departement;
use App\Models\Matiere;
use App\Models\User;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Str;

class MatiereSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {

        Matiere::factory(10)->create();

    }
}
