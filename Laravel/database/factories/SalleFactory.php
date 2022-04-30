<?php

namespace Database\Factories;

use App\Models\Departement;
use Illuminate\Database\Eloquent\Factories\Factory;

class SalleFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array
     */
    public function definition()
    {
        return [
            "titre" => $this->faker->word(),
            "departement_id" => Departement::get('id')->random(),
        ];
    }
}
