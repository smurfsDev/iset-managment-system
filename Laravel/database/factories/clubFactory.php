<?php

namespace Database\Factories;

use App\Models\DemandeCreationClub;
use App\Models\User;
use Illuminate\Database\Eloquent\Factories\Factory;

class clubFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array
     */
    public function definition()
    {
        return [
            // "nom"=>$this->faker->name,
            // "dateCreation"=>$this->faker->date,
            "responsableClub"=>User::get('id')->random(),
            "demandeCreationClubId"=>DemandeCreationClub::get('id')->random(),
            // "president"=>$this->faker->firstName().' '.$this->faker->lastName,
            // "vicePresident"=>$this->faker->firstName().' '.$this->faker->lastName,
        ];
    }
}
