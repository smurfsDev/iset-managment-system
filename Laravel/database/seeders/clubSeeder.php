<?php

namespace Database\Seeders;

use App\Models\club;
use Illuminate\Database\Seeder;

class clubSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        club::Factory(10)->create();
    }
}
