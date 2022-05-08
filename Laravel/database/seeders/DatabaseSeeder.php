<?php

namespace Database\Seeders;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;


class DatabaseSeeder extends Seeder
{
    /**
     * Seed the application's database.
     *
     * @return void
     */
    public function run()
    {


        // \App\Models\User::factory(10)->create();
        $this->call(RolesSeeder::class);
        $this->call(UserSeeder::class);
        $this->call(DemandeCreationClubSeeder::class);
        $this->call(clubSeeder::class);
        $this->call(MemberSeeder::class);
        $this->call(CategoryMaterielsSeeder::class);
        $this->call(MaterielsSeeder::class);
        $this->call(DemandeMaterielsSeeder::class);
        $this->call(DepartementSeeder::class);
        $this->call(SalleSeeder::class);
        $this->call(DemandeSalleSeeder::class);
        $this->call(ClasseSeeder::class);


    }
}
