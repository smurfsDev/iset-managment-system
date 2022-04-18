<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;

class DatabaseSeeder extends Seeder
{
    /**
     * Seed the application's database.
     *
     * @return void
     */
    public function run()
    {
        \App\Models\User::factory(1)->create();
        $this->call(DemandeCreationClubSeeder::class);
        $this->call(CategoryMaterielsSeeder::class);
        $this->call(MaterielsSeeder::class);
        $this->call(DemandeMaterielsSeeder::class);
        $this->call(DepartementSeeder::class);
        $this->call(ClasseSeeder::class);
        $this->call(DemandeClasseSeeder::class);

    }
}
