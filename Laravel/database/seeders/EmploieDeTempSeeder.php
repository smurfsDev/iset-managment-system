<?php

namespace Database\Seeders;

use App\Models\EmploieDeTemp;
use Illuminate\Database\Seeder;

class EmploieDeTempSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        EmploieDeTemp::factory(2)->create();
    }

}
