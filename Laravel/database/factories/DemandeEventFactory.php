<?php

namespace Database\Factories;

use App\Models\club;
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
            "nomEvent" => $this->faker->word(),
            "dateEvent" => $this->faker->date(),
            "description" => $this->faker->sentence(),
            "clubId" => club::get('id')->random(),
        ];
    }
}
