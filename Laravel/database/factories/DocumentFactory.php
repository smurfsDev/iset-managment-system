<?php

namespace Database\Factories;

use App\Models\User;
use Illuminate\Database\Eloquent\Factories\Factory;

class DocumentFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array
     */
    public function definition()
    {
        return [
            'nom' => $this->faker->sentence,
            'class' => $this->faker->sentence,
            'categorie' => $this->faker->sentence,
            'file' => $this->faker->sentence,
            'idResponsable' =>User::get('id')->random(),

        ];
    }
}
