<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use App\Models\Classe;
use App\Models\Departement;
use App\Models\User;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Str;

class ClasseSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        for ($i = 1; $i < 4; $i++) {
            for ($j = 1; $j < 2; $j++) {
                if ($i == 1) {
                    Classe::create([
                        'abreviation' => "TI" . $i . '' . $j,
                        'nom' => "Technologie d'information" . $i . '' . $j,
                        'departement_id' => 1,
                    ]);
                    Classe::create([
                        'abreviation' => "GM" . $i . '' . $j,
                        'nom' => "Genie mecanique" . $i . '' . $j,
                        'departement_id' => 3,
                    ]);
                    Classe::create([
                        'abreviation' => "GP" . $i . '' . $j,
                        'nom' => "Genie procedes" . $i . '' . $j,
                        'departement_id' => 5,
                    ]);
                    Classe::create([
                        'abreviation' => "GE" . $i . '' . $j,
                        'nom' => "Genie electrique" . $i . '' . $j,
                        'departement_id' => 2,
                    ]);
                    Classe::create([
                        'abreviation' => "AA" . $i . '' . $j,
                        'nom' => "AA" . $i . '' . $j,
                        'departement_id' => 4,
                    ]);
                }
                if ($i == 2 || $i == 3) {
                    Classe::create([
                        'abreviation' => "sem" . $i . '' . $j,
                        'nom' => "Systeme embarque et mobile" . $i . '' . $j,
                        'departement_id' => 1,
                    ]);
                    Classe::create([
                        'abreviation' => "dsi" . $i . '' . $j,
                        'nom' => "Developement des systemes d'information" . $i . '' . $j,
                        'departement_id' => 1,
                    ]);
                    Classe::create([
                        'abreviation' => "rsi" . $i . '' . $j,
                        'nom' => "Reseau" . $i . '' . $j,
                        'departement_id' => 1,
                    ]);
                    Classe::create([
                        'abreviation' => "CFM" . $i . '' . $j,
                        'nom' => "construction et fabrication mecanique" . $i . '' . $j,
                        'departement_id' => 3,
                    ]);
                    Classe::create([
                        'abreviation' => "MI" . $i . '' . $j,
                        'nom' => "Mintenance industriel" . $i . '' . $j,
                        'departement_id' => 3,
                    ]);
                    Classe::create([
                        'abreviation' => "PA" . $i . '' . $j,
                        'nom' => "Procédés Alimentaires" . $i . '' . $j,
                        'departement_id' => 5,
                    ]);
                    Classe::create([
                        'abreviation' => "PC" . $i . '' . $j,
                        'nom' => "Procédés Chimiques" . $i . '' . $j,
                        'departement_id' => 5,
                    ]);
                    Classe::create([
                        'abreviation' => "EI" . $i . '' . $j,
                        'nom' => "Electricité Industrielle" . $i . '' . $j,
                        'departement_id' => 2,
                    ]);
                    Classe::create([
                        'abreviation' => "AII" . $i . '' . $j,
                        'nom' => "Automatismes et informatique industrielle" . $i . '' . $j,
                        'departement_id' => 2,
                    ]);
                    Classe::create([
                        'abreviation' => "EI" . $i . '' . $j,
                        'nom' => "Electronique industrielle" . $i . '' . $j,
                        'departement_id' => 2,
                    ]);
                    Classe::create([
                        'abreviation' => "LTI" . $i . '' . $j,
                        'nom' => "Logistique et Transport International" . $i . '' . $j,
                        'departement_id' => 4,
                    ]);
                    Classe::create([
                        'abreviation' => "MA" . $i . '' . $j,
                        'nom' => "Management des affaires" . $i . '' . $j,
                        'departement_id' => 4,
                    ]);
                    Classe::create([
                        'abreviation' => "CF" . $i . '' . $j,
                        'nom' => "Comptabilité Finance" . $i . '' . $j,
                        'departement_id' => 4,
                    ]);
                    Classe::create([
                        'abreviation' => "MD" . $i . '' . $j,
                        'nom' => "Marketing Digital" . $i . '' . $j,
                        'departement_id' => 4,
                    ]);
                }
            }
        }
        for ($i = 1; $i <= 25; $i++) {
            $usr = User::create([
                'name' => "student" . $i,
                'email' => "student" . $i . "@example.com",
                'email_verified_at' => now(),
                "sexe" => array_rand(["homme", "femme"]),
                'password' => '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', // password
                'remember_token' => Str::random(10),
            ]);
            $class = Classe::get()->random();
            $dep = $class->departement_id;
            DB::insert('insert into role_user (user_id, role_id,status,classe,department) values (?, ?,?,?,?)', [$usr->id, 2, 1, $class->id,$dep]);
        }
        for ($i = 1; $i <= 25; $i++) {
            $usr = User::create([
                'name' => "teacher" . $i,
                'email' => "teacher" . $i . "@example.com",
                'email_verified_at' => now(),
                "sexe" => array_rand(["homme", "femme"]),
                'password' => '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', // password
                'remember_token' => Str::random(10),
            ]);
            DB::insert('insert into role_user (user_id, role_id,status,department) values (?, ?,?,?)', [$usr->id, 7, 1, Departement::all()->random()->id]);
        }
    }

    // Classe::factory(10)->create();
}
