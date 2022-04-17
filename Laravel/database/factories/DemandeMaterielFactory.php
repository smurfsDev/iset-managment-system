<?php

namespace Database\Factories;

use App\Models\CategorieMateriel;
use App\Models\User;
use Illuminate\Database\Eloquent\Factories\Factory;

class DemandeMaterielFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array
     */
    public function definition()
    {
        return [

            "idResponsableClub" => User::get('id')->random(),
            "idDestinataire" => User::get('id')->random(),
            "dateEmploi" => $this->faker->date(),
            "dateDeRemise" => $this->faker->date(),
            "status"  =>$this->faker->numberBetween(1,3),
            "reponse" =>$this->faker->sentence(),
            "idCategorie" =>CategorieMateriel::get('id')->random(),

        ];
    }
}
