<?php

namespace Database\Seeders;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Str;
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
        $this->call(clubSeeder::class);
        $this->call(MemberSeeder::class);
        $this->call(RolesSeeder::class);
        DB::insert('insert into role_user (user_id, role_id) values (?, ?)', [1, 1]);
        DB::insert('insert into role_user (user_id, role_id) values (?, ?)', [2, 2]);
        $this->call(CategoryMaterielsSeeder::class);
        $this->call(MaterielsSeeder::class);
        $this->call(DemandeMaterielsSeeder::class);
        $this->call(DepartementSeeder::class);
        $this->call(SalleSeeder::class);
        $this->call(DemandeSalleSeeder::class);

    }
}
