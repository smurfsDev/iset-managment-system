<?php

namespace Database\Seeders;

use App\Models\Salle;
use Illuminate\Database\Seeder;

class SalleSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $Dep = ["I","E","M","GE","P"];
        for ($j = 0; $j <= 1; $j++) {
            for ($i = 0; $i < 10; $i++) {
                Salle::create([
                    'titre' => "Salle $Dep[$j]$i",
                    'departement_id' => $j+1,
                ]);
            }
        }
    }
}
