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
        \App\Models\User::factory(10)->create();

        $this->call(DemandeCreationClubSeeder::class);
        $this->call(clubSeeder::class);
        $this->call(MemberSeeder::class);

    }
}
