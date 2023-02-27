import { Body, Controller, Delete, Get, Param, Post, Put } from '@nestjs/common';
import { TeacherDto } from '../dto/teacher-dto.dto';
import { TeacherService } from '../services/teacher.service';


@Controller('Teacher')
export class TeacherController {
    constructor(private readonly teacherService: TeacherService) { }

    @Post()
    async create(@Body() teacherDto: TeacherDto) {
        const res = this.teacherService.create(teacherDto);
        return res;
    }

    @Get()
    async findAll() {
        return this.teacherService.findAll();
    }

    @Put('/:id')
    update(@Param('id') id: string, @Body() teacherDto: TeacherDto) {
        return this.teacherService.update(id, teacherDto);
    }

    @Delete('/:id')
    delete(@Param('id') id: string) {
        return this.teacherService.delete(id);
    }
}