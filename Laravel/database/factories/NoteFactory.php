<?php

namespace Database\Factories;

use App\Models\Matiere;
use App\Models\User;
use Illuminate\Database\Eloquent\Factories\Factory;

class NoteFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array
     */
    public function definition()
    {
        return [
            "note" => $this->faker->numberBetween(0, 20),
            "student_id" => User::all()->random()->id,
            "matiere_id" => Matiere::all()->random()->id,
        ];
    }
}
