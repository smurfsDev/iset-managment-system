<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class header extends Model
{
    use HasFactory;
    protected $table = 'headers';
    protected $fillable = [
        "petiteDescription",
        "backgroundImage",
        "idClub"
    ];
   
}
