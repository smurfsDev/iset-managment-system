<?php

namespace Database\Factories;

use Illuminate\Database\Eloquent\Factories\Factory;
use App\Models\Departement;

class ClasseFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array
     */
    public function definition()
    {
        return [
            'abreviation' => $this->faker->word,
            'nom' => $this->faker->word,
            'departement_id' => Departement::get('id')->random(),
        ];
    }
}
