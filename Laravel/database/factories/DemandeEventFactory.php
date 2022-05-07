<?php

namespace Database\Factories;

use App\Models\User;
use Illuminate\Database\Eloquent\Factories\Factory;

class DemandeEventFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array
     */
    public function definition()
    {
        return [
            "responsableClubId" => User::get('id')->random(),
            "administrateurId" => User::get('id')->random(),
            "dateEvent" => $this->faker->date(),
            "description" => $this->faker->sentence(),
        ];
    }
}
