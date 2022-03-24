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
        \App\Models\DemandeCreationClub::create([
                "nomClub"=> "test",
                "logo"=> "test",
                "dateCreation"=> "2022-03-16",
                "activite"=> "test",
                "president"=> "test",
                "vicePresident"=> "test",
                "responsableClubId"=> "1"]
        );

    }
}
