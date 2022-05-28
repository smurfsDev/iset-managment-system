<?php

namespace Database\Factories;

use Illuminate\Database\Eloquent\Factories\Factory;

class EmploieDeTempFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array
     */
    public function definition()
    {
        return [
            "title" => $this->faker->word(),
            "classes_id" => $this->faker->randomDigit(),   
            "file" => $this->faker->word(),
            "idResponsable" => $this->faker->randomDigit(),  
            
        ];
    }
}
