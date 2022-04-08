<?php

namespace Database\Factories;

use App\Models\User;
use Illuminate\Database\Eloquent\Factories\Factory;

class DemandeCreationClubFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array
     */
    public function definition()
    {
        return [
            "nomClub" => $this->faker->word(),
            "logo" => $this->faker->word(),
            "dateCreation" => $this->faker->date(),
            "activite" => $this->faker->sentence(),
            "president" => $this->faker->firstName().' '.$this->faker->lastName,
            "vicePresident" => $this->faker->firstName().' '.$this->faker->lastName,
            "responsableClubId" => User::get('id')->random()
        ];
    }
}
