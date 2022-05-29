<?php

namespace Database\Seeders;

use App\Models\TypeCat;
use Illuminate\Database\Seeder;

class TypeCatSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
            TypeCat::factory(10)->create();

    }
}
